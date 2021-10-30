package no.ntnu.idata2001.contacts.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class AdressBookDBHandler implements Serializable, Iterable<ContactDetails>, AddressBook{

    private EntityManagerFactory efact;

    public AdressBookDBHandler(){
        efact = Persistence.createEntityManagerFactory("my-app");
    }

    @Override
    public void addContact(ContactDetails contactDetails) {
        EntityManager entityManager = efact.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(contactDetails);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public void removeContact(String phoneNumber) {
        EntityManager entityManager = efact.createEntityManager();

        ContactDetails cd = entityManager.find(ContactDetails.class, phoneNumber);
        entityManager.getTransaction().begin();
        entityManager.remove(cd);
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    @Override
    public Collection<ContactDetails> getAllContacts() {
        EntityManager entityManager = efact.createEntityManager();

        return entityManager.createQuery("select e from ContactDetails e", ContactDetails.class).getResultList();
    }

    @Override
    public Iterator<ContactDetails> iterator() {
        return getAllContacts().iterator();
    }

    @Override
    public void close() {
        efact.close();
    }
}
