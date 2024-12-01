import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


    public class ReservationRepositoryTest {
        @Test
        public void testAddReservation() {
            ReservationRepository repository = new ReservationRepository();
            Reservation reservation = new Reservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
            repository.add(reservation);

            assertTrue(repository.exists("1", "020900581"));
        }

        @Test
        public void testRemoveReservation() {
            ReservationRepository repository = new ReservationRepository();
            Reservation reservation = new Reservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
            repository.add(reservation);

            assertTrue(repository.remove("1", "020900581"));
            assertFalse(repository.exists("1", "020900581"));
        }
                  // Edge Case: Test removing a reservation that doesn't exist
        @Test
    public void testRemoveNonExistentReservation() {
        ReservationRepository repository = new ReservationRepository();
        // Try to remove a reservation that doesn't exist
        boolean result = repository.remove("999", "nonexistentCPR");
        // The method should return false, as the reservation does not exist
        assertFalse(result);
        }
    // Edge Case: Test removing from an empty repository
    @Test
    public void testRemoveFromEmptyRepository() {
        ReservationRepository repository = new ReservationRepository();
        // Try to remove a reservation from an empty repository
        boolean result = repository.remove("1", "020900581");
        // The method should return false, as there are no reservations in the repository
        assertFalse(result);
        }
        @Test
        public void testGetAllReservations() {
            ReservationRepository repository = new ReservationRepository();
            Reservation reservation1 = new Reservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
            Reservation reservation2 = new Reservation("Fatema Mohamed", "Standard", "14", 100.0, "030056722", "00968-54379124", LocalDate.now().minusDays(1));
            repository.add(reservation1);
            repository.add(reservation2);

            assertEquals(2, repository.getAll().size());
        }

        @Test
        public void testExists() {
            ReservationRepository repository = new ReservationRepository();
            Reservation reservation = new Reservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
            repository.add(reservation);

            assertTrue(repository.exists("1", "020900581"));
            assertFalse(repository.exists("1", "123456789"));
        }
    }