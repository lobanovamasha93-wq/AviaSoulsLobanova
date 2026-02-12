package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

public class AviaSoulsTest {

    @Test
    public void shouldCompareTicketsByPrice() {
        Ticket ticket1 = new Ticket("MOW", "LED", 100, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "LED", 200, 10, 12);
        Ticket ticket3 = new Ticket("MOW", "LED", 100, 10, 12);

        Assertions.assertTrue(ticket1.compareTo(ticket2) < 0);
        Assertions.assertTrue(ticket2.compareTo(ticket1) > 0);
        Assertions.assertEquals(0, ticket1.compareTo(ticket3));
    }

    @Test
    public void shouldSearchAndSortByPrice() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("MOW", "LED", 300, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "LED", 100, 12, 14);
        Ticket ticket3 = new Ticket("MOW", "LED", 200, 14, 16);
        Ticket ticket4 = new Ticket("MOW", "KZN", 50, 10, 11); // Другой маршрут

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] expected = { ticket2, ticket3, ticket1 }; // 100, 200, 300
        Ticket[] actual = manager.search("MOW", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndReturnSingleTicket() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("MOW", "LED", 300, 10, 12);
        manager.add(ticket1);

        Ticket[] expected = { ticket1 };
        Ticket[] actual = manager.search("MOW", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndReturnEmptyArray() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("MOW", "LED", 300, 10, 12);
        manager.add(ticket1);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("LED", "MOW"); // Обратный маршрут

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsByFlightTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket ticket1 = new Ticket("MOW", "LED", 100, 10, 12);
        Ticket ticket2 = new Ticket("MOW", "LED", 100, 10, 15);

        Assertions.assertTrue(comparator.compare(ticket1, ticket2) < 0);
        Assertions.assertTrue(comparator.compare(ticket2, ticket1) > 0);
        Assertions.assertEquals(0, comparator.compare(ticket1, ticket1));
    }

    @Test
    public void shouldSearchAndSortByFlightTime() {
        AviaSouls manager = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();


        Ticket ticket1 = new Ticket("MOW", "LED", 100, 10, 15);
        Ticket ticket2 = new Ticket("MOW", "LED", 200, 10, 12);
        Ticket ticket3 = new Ticket("MOW", "LED", 300, 10, 13);

        Ticket ticket4 = new Ticket("MOW", "KZN", 500, 10, 20);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] expected = { ticket2, ticket3, ticket1 };
        Ticket[] actual = manager.searchAndSortBy("MOW", "LED", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}