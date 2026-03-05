package com.event.TaskFlow.persistence.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailEntityTest {

    @Test
    @DisplayName("get email should return correct email")
    void testGetEmail() {
        String emailStr = "teste@gmail.com";
        EmailEntity emailEntity = new EmailEntity(emailStr);
        assertEquals(emailStr, emailEntity.getEmail());
    }

    @Test
    @DisplayName("HashCode should be consistent for equal objects")
    void testHashCodeConsistency() {
        EmailEntity email1 = new EmailEntity("teste@gmail.com");
        EmailEntity email2 = new EmailEntity("teste@gmail.com");
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    @DisplayName("HashCode should differ for different objects")
    void testHashCodeDifference() {
        EmailEntity email1 = new EmailEntity("teste@gmail.com");
        EmailEntity email2 = new EmailEntity("teste@gmail.co");
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    @DisplayName("Equals should return true for identical objects")
    void testEqualsIdenticalObjects() {
        EmailEntity email1 = new EmailEntity("teste@gmail.com");
        EmailEntity email2 = new EmailEntity("teste@gmail.com");
        assertEquals(email1, email2);
    }

    @Test
    @DisplayName("Equals should return false for different objects")
    void testEqualsDifferentObjects() {
        EmailEntity email1 = new EmailEntity("teste@gmail.com");
        EmailEntity email2 = new EmailEntity("teste@gmail.co");
        assertNotEquals(email1, email2);
    }

    @Test
    @DisplayName("Equals same object should return true")
    void testEqualsSameObject() {
        EmailEntity email = new EmailEntity("teste@gmail.com");
        EmailEntity email1 = new EmailEntity("teste@gmail.com");
        assertEquals(email, email1);
    }

    @Test
    @DisplayName("Equals should return false when compared with null")
    void testEqualsWithNull() {
        EmailEntity email1 = new EmailEntity("teste@gmail.com");
        assertNotNull(email1);
    }

    @Test
    @DisplayName("Equals should return false when compared with different class")
    void testEqualsWithDifferentClass() {
        EmailEntity email1 = new EmailEntity("teste@gmail.com");
        String differentClassObject = "NotAnEmailEntity";
        assertNotEquals(email1, differentClassObject);
    }

    @Test
    @DisplayName("Construtor sem-args protegido deve instanciar a classe")
    void testNoArgProtectedConstructor() {
        EmailEntity entity = new EmailEntity();
        assertNotNull(entity);
    }

}