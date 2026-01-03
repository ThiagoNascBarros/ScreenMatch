package br.com.alura.screenmatch.Service;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class ConsumerOpenAI {
    public static final Dotenv dotenv = Dotenv.load();
    private static final String KEY = dotenv.get("API_KEY_GEM");

    public static String getTranslate(String text) {
        ChatModel gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(KEY)
                .modelName("gemini-2.5-flash")
                .build();

        var response = gemini.chat(ChatRequest.builder()
                .messages(UserMessage.from("Just translate for pt/br the following passage without telling me anything else: " + text))
                .build());

        return response.aiMessage().text();
    }

}
