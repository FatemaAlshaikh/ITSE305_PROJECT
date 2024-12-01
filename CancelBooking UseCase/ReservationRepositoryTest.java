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