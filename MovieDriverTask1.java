import java.util.Scanner;

public class MovieDriverTask1
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

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

        System.out.println(movie.toString());

        keyboard.close();
    }
}