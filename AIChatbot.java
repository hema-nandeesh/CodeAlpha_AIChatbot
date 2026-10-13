
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AIChatbot {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> conversationHistory = new ArrayList<>();

    private static final String BOT_NAME = "Nova";

    private static String getTime() {
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("hh:mm a"));
    }

    private static void addMessage(String sender, String message) {
        String entry = "[" + getTime() + "] " + sender + ": " + message;
        conversationHistory.add(entry);
    }

    private static String getResponse(String input) {
        String message = input.toLowerCase(Locale.ROOT).trim();

        if (message.matches(".*\\b(hi|hello|hey|hii|hiii)\\b.*")) {
            return "Hello! Welcome to Nova Chatbot. How can I help you today?";
        }

        if (message.contains("your name") || message.contains("who are you")) {
            return "I am Nova, a Java-based chatbot created to answer your questions.";
        }

        if (message.matches(".*\\bhow are you\\b.*")) {
            return "I am doing great! Thanks for asking. How are you?";
        }

        if (message.matches(".*\\bhow are you doing\\b.*")) {
            return "I am doing well! What would you like to talk about?";
        }

        if (message.contains("your creator") || message.contains("who created you")) {
            return "I was created using Java programming and rule-based response logic.";
        }

        if (message.matches(".*\\b(time|current time)\\b.*")) {
            return "The current system time is " + getTime() + ".";
        }

        if (message.matches(".*\\b(date|today's date|today date)\\b.*")) {
            return "Today's date is "
                    + LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".";
        }

        if (message.contains("java")) {
            return "Java is an object-oriented programming language used "
                    + "to build desktop, web, and enterprise applications.";
        }

        if (message.contains("python")) {
            return "Python is a popular programming language known for "
                    + "its simple syntax and use in automation, data science, and AI.";
        }

        if (message.contains("programming")) {
            return "Programming means writing instructions that tell a "
                    + "computer how to perform tasks.";
        }

        if (message.contains("artificial intelligence")
                || message.matches(".*\\bai\\b.*")) {
            return "Artificial Intelligence enables computers to perform "
                    + "tasks that normally require human intelligence.";
        }

        if (message.contains("machine learning")) {
            return "Machine learning is a branch of AI in which computers "
                    + "learn patterns from data.";
        }

        if (message.contains("college") || message.contains("student")) {
            return "College is a great place to learn, build projects, "
                    + "develop skills, and prepare for your career.";
        }

        if (message.contains("internship")) {
            return "An internship provides practical experience. Try to "
                    + "build projects, learn new skills, and maintain a portfolio.";
        }

        if (message.contains("resume") || message.contains("cv")) {
            return "A good resume includes your education, skills, projects, "
                    + "certifications, and relevant experience.";
        }

        if (message.contains("interview")) {
            return "Prepare your introduction, revise technical concepts, "
                    + "practice coding, and learn about the company.";
        }

        if (message.contains("project")) {
            return "Start with a clear problem statement, divide the work "
                    + "into small tasks, write the code, and test each feature.";
        }

        if (message.contains("thank")) {
            return "You're welcome! I am happy to help.";
        }

        if (message.matches(".*\\b(joke|tell me a joke)\\b.*")) {
            return "Why do programmers prefer dark mode? Because light attracts bugs!";
        }

        if (message.matches(".*\\bhelp\\b.*")) {
            return "You can ask me about Java, Python, AI, machine learning, "
                    + "college, internships, resumes, interviews, or projects.";
        }

        if (message.matches(".*\\b(bye|goodbye|see you)\\b.*")) {
            return "Goodbye! It was nice talking to you. Have a great day!";
        }

        List<String> fallbackResponses = Arrays.asList(
                "That sounds interesting! Could you tell me more?",
                "I am still learning. Try asking me about Java, AI, or internships.",
                "I don't have a specific answer for that yet. Could you rephrase your question?",
                "Interesting question! I can answer questions based on my built-in knowledge."
        );

        int index = Math.abs(message.hashCode() % fallbackResponses.size());
        return fallbackResponses.get(index);
    }

    private static void saveConversation() {
        try (FileWriter writer = new FileWriter("chat_history.txt", true)) {
            writer.write("\n========== Chat Session ==========\n");

            for (String message : conversationHistory) {
                writer.write(message + System.lineSeparator());
            }

            writer.write("========== End of Session ==========\n");
            System.out.println("Conversation saved to chat_history.txt");

        } catch (IOException e) {
            System.out.println("Unable to save conversation: " + e.getMessage());
        }
    }

    private static void showHelp() {
        System.out.println("\nYou can ask me about:");
        System.out.println("- Java and Python");
        System.out.println("- Artificial intelligence and machine learning");
        System.out.println("- College and student life");
        System.out.println("- Internships and projects");
        System.out.println("- Resumes and interviews");
        System.out.println("- Time and date");
        System.out.println("\nCommands:");
        System.out.println("- help : Show available topics");
        System.out.println("- history : Display this session's conversation");
        System.out.println("- save : Save the conversation to a file");
        System.out.println("- exit : End the chat");
    }

    private static void showHistory() {
        System.out.println("\n========== CONVERSATION HISTORY ==========");

        if (conversationHistory.isEmpty()) {
            System.out.println("No conversation yet.");
            return;
        }

        for (String message : conversationHistory) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("       NOVA - JAVA AI CHATBOT");
        System.out.println("======================================");
        System.out.println("Hello! I am " + BOT_NAME + ".");
        System.out.println("Type 'help' to see what I can do.");
        System.out.println("Type 'exit' to end the conversation.");

        addMessage(BOT_NAME, "Hello! I am Nova. How can I help you?");

        while (true) {
            System.out.print("\nYou: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(BOT_NAME + ": Please enter a message.");
                continue;
            }

            addMessage("You", input);

            if (input.equalsIgnoreCase("exit")) {
                String response = "Goodbye! Thanks for chatting with me.";
                System.out.println(BOT_NAME + ": " + response);
                addMessage(BOT_NAME, response);
                break;
            }

            if (input.equalsIgnoreCase("help")) {
                showHelp();
                continue;
            }

            if (input.equalsIgnoreCase("history")) {
                showHistory();
                continue;
            }

            if (input.equalsIgnoreCase("save")) {
                saveConversation();
                continue;
            }

            String response = getResponse(input);
            System.out.println(BOT_NAME + ": " + response);
            addMessage(BOT_NAME, response);
        }

        saveConversation();
        scanner.close();
        System.out.println("Chatbot closed successfully.");
    }
}
