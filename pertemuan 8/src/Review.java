import java.time.LocalDate;

public class Review {
    private final String reviewer;
    private final int rating;
    private final String comment;
    private final LocalDate reviewDate;

    public Review(String reviewer, int rating, String comment) {
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format("%s (%d/5): %s [%s]", reviewer, rating, comment, reviewDate);
    }

    public int getRating() {
        return rating;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }
}