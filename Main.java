package movieRecomend;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("WELCOME TO MOVIE RECOMMENDATION SYSTEM");
        System.out.println("=============================================");

        System.out.println("Select the language:");
        System.out.println("1. KANNADA\n2. ENGLISH\n3. HINDI\n4. TAMIL\n5. MALAYALAM");
        int language = input.nextInt();
        recommendMovies(language);

        System.out.println("=============================================");
        System.out.println("Would you like to provide feedback? (yes/no)");
        String feedbackChoice = input.next();
        if (feedbackChoice.equalsIgnoreCase("yes")) {
            System.out.println("Please provide your feedback:");
            input.nextLine(); 
            String feedback = input.nextLine();
            analyzeFeedbackSentiment(feedback);
        }

        System.out.println("............THANK YOU, VISIT AGAIN............");
        System.out.println("=============================================");
    }

    public static void recommendMovies(int language) {
        Scanner input = new Scanner(System.in);
        String selectedLanguage = getLanguageName(language);
        System.out.println("Your selected language is " + selectedLanguage);
        System.out.println("=============================================");

        System.out.println("Select the genre:");
        System.out.println("1. ACTION\n2. ADVENTURE\n3. COMEDY\n4. DRAMA\n5. HORROR");
        int genre = input.nextInt();
        recommendMoviesByGenre(language, genre);
    }

    public static String getLanguageName(int language) {
        String[] languages = {"KANNADA", "ENGLISH", "HINDI", "TAMIL", "MALAYALAM"};
        return languages[language - 1];
    }

    public static void recommendMoviesByGenre(int language, int genre) {
        String[][] recommendedMovies = {
            // KANNADA, ENGLISH, HINDI, TAMIL, MALAYALAM
            {"KABZA", "MISSION IMPOSSIBLE-4", "PATHAN", "THUPPAKI", "PULI MURUGAN"},
            {"KGF-2", "TRANSPORTERS", "FORCE", "VEDALAM", "LUCIGER"},
            {"UGRAMM", "JHON WICK", "DON", "KABALI", "BHEESHMA PARVAM"},
            {"SALAGA", "THE MEG", "ATTACK", "ANJAN", "CHRISTOPHER"},
            {"GANDHADA GUDI-2021", "FAST AND FURIOUS X", "SATHYAMEVA JAYATHE", "KOCHDIYAN", "MINAL MURULI"}
        };

        String selectedMovieLanguage = getLanguageName(language);
        String selectedGenre = getGenreName(genre);

        System.out.println("These are the recommended movies for you:");
        String[] movies = recommendedMovies[genre - 1];
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i + 1) + ". " + movies[i]);
        }
    }

    public static String getGenreName(int genre) {
        String[] genres = {"ACTION", "ADVENTURE", "COMEDY", "DRAMA", "HORROR"};
        return genres[genre - 1];
    }

    public static void analyzeFeedbackSentiment(String feedback) {
        String[] positiveKeywords = {"good", "great", "excellent", "awesome"};
        String[] negativeKeywords = {"bad", "poor", "disappointing", "awful","Not good"};

        int positiveCount = countKeywords(feedback, positiveKeywords);
        int negativeCount = countKeywords(feedback, negativeKeywords);

        if (positiveCount > negativeCount) {
            System.out.println("Thank you for your positive feedback!");
        } else if (negativeCount > positiveCount) {
            System.out.println("We're sorry to hear that. We'll work to improve.");
        } else {
            System.out.println("Thank you for your feedback.");
        }
    }

    public static int countKeywords(String text, String[] keywords) {
        int count = 0;
        text = text.toLowerCase();
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                count++;
            }
        }
        return count;
    }
   
    
}
//ratting hisab re recoment the movie mean 5 star hisab re all 5 star movie
//hero hisab re recomendationn gener and category director made by movie
//add more funtionality

