import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

class Expense {
    public String description;
    public double amount;
    public String category;
    public String date;

    @Override
    public String toString() {
        return description + ": $" + amount + " (Category: " + category + ", Date: " + date + ")";
    }
}

public class ChatbotService {
    private static final String AI_API_URL = "https://api.openai.com/v1/completions";
    private static final String AI_API_KEY = "your-api-key-here"; // Replace with your actual API key
    private static final String JSON_FILE_PATH = "data/expenses.json"; // Path to your JSON file

    public String handleUserInput(String input) {
        try {
            List<Expense> expenses = getExpensesFromJsonFile(); // Fetch data from JSON file
            String response = getAIResponse(input, expenses); // Pass data to AI
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "I'm sorry, I couldn't process your request. Please try again later.";
        }
    }

    private List<Expense> getExpensesFromJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(JSON_FILE_PATH);

        if (!file.exists()) {
            throw new IOException("Expenses file not found!");
        }

        return objectMapper.readValue(file, new TypeReference<List<Expense>>() {});
    }

    private String getAIResponse(String input, List<Expense> expenses) throws IOException {
        // Format the expense data to include in the AI prompt
        StringBuilder expenseSummary = new StringBuilder();
        for (Expense expense : expenses) {
            expenseSummary.append(expense.toString()).append("\n");
        }

        String prompt = String.format(
            "You are an expense tracker chatbot. Here is the user's expense data:\n%s\n\nAnswer the following question: \"%s\"",
            expenseSummary, input
        );

        // Prepare the request body
        String requestBody = String.format(
            "{\"model\": \"text-davinci-003\", \"prompt\": \"%s\", \"max_tokens\": 150, \"temperature\": 0.7}",
            prompt.replace("\"", "\\\"") // Escape double quotes in the prompt
        );

        // Make the API call
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(AI_API_URL)
            .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
            .addHeader("Authorization", "Bearer " + AI_API_KEY)
            .addHeader("Content-Type", "application/json")
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Extract the AI response from the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.body().string();
            return objectMapper.readTree(responseBody).get("choices").get(0).get("text").asText().trim();
        }
    }

    public static void main(String[] args) {
        ChatbotService chatbotService = new ChatbotService();

        // Example usage
        String userInput = "What are my expenses?";
        String response = chatbotService.handleUserInput(userInput);
        System.out.println("Chatbot Response: " + response);
    }
}