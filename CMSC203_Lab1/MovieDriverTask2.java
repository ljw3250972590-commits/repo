import java.util.Scanner;

public class MovieDriverTask2
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        String answer;

        do
        {
            Movie movie = new Movie();

            System.out.println("Enter the name of a movie");
            String title = keyboard.nextLine();
            movie.setTitle(title);

            System.out.println("Enter the rating of the movie");
            String rating = keyboard.nextLine();
            movie.setRating(rating);

            System.out.println("Enter the number of tickets sold for this movie");
            int tickets = keyboard.nextInt();
            movie.setSoldTickets(tickets);

            keyboard.nextLine();

            System.out.println(movie.toString());

            System.out.println("Do you want to enter another? (y or n)");
            answer = keyboard.nextLine();

        } while (answer.equalsIgnoreCase("y"));

        System.out.println("Goodbye");

        keyboard.close();
    }
}