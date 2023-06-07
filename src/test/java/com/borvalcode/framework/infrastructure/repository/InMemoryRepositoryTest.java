package com.borvalcode.framework.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.borvalcode.framework.domain.entity.Entity;

class InMemoryRepositoryTest {

    private final HashMap<Book.Key, Book> storage = new HashMap<>();
    private final InMemoryRepository<Book.Key, Book> repository = new InMemoryRepository<>(storage);

    @Test
    void puts_value_in_repository() {
        Book aBook = new Book(new Book.Id(1L), new Book.Name("foo"));

        repository.store(aBook);

        Book actual = storage.get(aBook.getPrimaryKey());
        assertEquals(aBook, actual);
    }

    @Test
    void gets_a_value_in_repository() {
        Book aBook = new Book(new Book.Id(1L), new Book.Name("foo"));

        storage.put(aBook.getPrimaryKey(), aBook);

        assertEquals(aBook, repository.get(aBook.getPrimaryKey()).get());
        assertEquals(aBook, repository.get(aBook.getId()).get());
        assertEquals(aBook, repository.get(aBook.getName()).get());
    }

    private static final class Book implements Entity<Book.Key> {
        private final Id id;
        private final Name name;

        private Book(Id id, Name name) {
            this.id = id;
            this.name = name;
        }

        public Id getId() {
            return id;
        }

        public Name getName() {
            return name;
        }

        @Override
        public Id getPrimaryKey() {
            return getId();
        }

        @Override
        public List<Key> getKeys() {
            return Arrays.asList(id, name);
        }

        private interface Key extends Entity.Key {}

        private static final class Id implements Key {
            private final long value;

            public Id(long value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Id)) return false;
                Id id = (Id) o;
                return value == id.value;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        private static final class Name implements Key {
            private final String value;

            public Name(String value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Name)) return false;
                Name name = (Name) o;
                return value.equals(name.value);
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Book)) return false;
            Book book = (Book) o;
            return id.equals(book.id) && name.equals(book.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
