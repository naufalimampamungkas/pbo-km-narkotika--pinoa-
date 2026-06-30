package model;

/**
 * Abstract parent class for all domain entities in the KMS system.
 * Provides common fields and behavior that all entities share.
 *
 * <p>Demonstrates OOP concepts: abstraction, inheritance base.</p>
 *
 * @author Knowledge/DB Engineer
 * @version 1.0
 */
public abstract class DataEntity {

    /** Unique identifier for the entity */
    private String id;

    /** Timestamp when the entity was created (as String for simplicity) */
    private String createdAt;

    /**
     * No-arg constructor. Sets default values.
     */
    public DataEntity() {
        this.id = "";
        this.createdAt = "";
    }

    /**
     * Parameterized constructor.
     *
     * @param id        the entity ID
     * @param createdAt the creation timestamp
     */
    public DataEntity(String id, String createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    // ======================== Getters & Setters ========================
    /**
     * Mengembalikan ID entity.
     *
     * @return ID entity
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // ======================== Abstract Methods ========================

    public abstract void displayInfo();

    @Override
    public abstract String toString();
}