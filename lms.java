import java.util.*;

public class Main {
    // ----- Data model -----
    static class Course {
        String title;
        String videoUrl;
        List<String> enrolledStudents = new ArrayList<>();
        // question -> correct answer
        Map<String, String> quiz = new LinkedHashMap<>();
        Course(String title, String videoUrl) { this.title = title; this.videoUrl = videoUrl; }
    }

    // store courses by lowercase title for easy matching
    static Map<String, Course> courses = new LinkedHashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Learning Management System ---");
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Add Quiz to Course");
            System.out.println("4. Take Quiz");
            System.out.println("5. Stream Video");
            System.out.println("6. Exit");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addCourse();
                case 2 -> enrollStudent();
                case 3 -> addQuiz();
                case 4 -> takeQuiz();
                case 5 -> streamVideo();
                case 6 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice, pick 1-6.");
            }
        }
    }

    // ---------- Features ----------
    static void addCourse() {
        String title = readLine("Enter course title: ");
        String url = readLine("Enter video URL: ");
        String key = title.toLowerCase();
        courses.put(key, new Course(title, url));
        System.out.println("Course added successfully.");
    }

    static void enrollStudent() {
        Course c = requireCourse();
        if (c == null) return;
        String student = readLine("Enter student name: ");
        c.enrolledStudents.add(student);
        System.out.println("Student enrolled successfully.");
    }

    static void addQuiz() {
        Course c = requireCourse();
        if (c == null) return;
        String q = readLine("Enter question: ");
        String a = readLine("Enter answer: ");
        c.quiz.put(q, a);
        System.out.println("Quiz question added.");
    }

    static void takeQuiz() {
        Course c = requireCourse();
        if (c == null) return;
        if (c.quiz.isEmpty()) {
            System.out.println("No quiz questions yet.");
            return;
        }
        int score = 0, total = c.quiz.size();
        for (Map.Entry<String, String> e : c.quiz.entrySet()) {
            System.out.println("Q: " + e.getKey());
            String ans = readLine("Your answer: ");
            if (ans.trim().equalsIgnoreCase(e.getValue().trim())) score++;
        }
        System.out.println("You scored " + score + "/" + total);
    }

    static void streamVideo() {
        Course c = requireCourse();
        if (c == null) return;
        System.out.println("Streaming video: " + c.videoUrl);
        System.out.println("[Simulated streaming... 0% ... 25% ... 50% ... 75% ... 100%]");
    }

    // ---------- Helpers ----------
    static Course requireCourse() {
        String title = readLine("Enter course title: ");
        Course c = courses.get(title.toLowerCase());
        if (c == null) System.out.println("Course not found. Add it first (option 1).");
        return c;
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (Exception e) { System.out.println("Please enter a number (e.g., 1)."); }
        }
    }

    static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
