import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface Expirable {
    boolean isExpired();
    LocalDate getExpiryDate();
    
    default long daysUntilExpiry() {
        return ChronoUnit.DAYS.between(LocalDate.now(), getExpiryDate());
    }
}