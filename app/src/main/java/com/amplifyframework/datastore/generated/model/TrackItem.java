package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the TrackItem type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TrackItems")
@Index(name = "byUserId", fields = {"userId","pin","createdAt"})
public final class TrackItem implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField USER_ID = field("userId");
  public static final QueryField PIN = field("pin");
  public static final QueryField DESCRIPTION = field("description");
  public static final QueryField TYPE = field("type");
  public static final QueryField OWNER = field("owner");
  public static final QueryField CREATED_AT = field("createdAt");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String userId;
  private final @ModelField(targetType="String", isRequired = true) String pin;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="String") String type;
  private final @ModelField(targetType="String") String owner;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime createdAt;
  public String getId() {
      return id;
  }
  
  public String getUserId() {
      return userId;
  }
  
  public String getPin() {
      return pin;
  }
  
  public String getDescription() {
      return description;
  }
  
  public String getType() {
      return type;
  }
  
  public String getOwner() {
      return owner;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  private TrackItem(String id, String userId, String pin, String description, String type, String owner, Temporal.DateTime createdAt) {
    this.id = id;
    this.userId = userId;
    this.pin = pin;
    this.description = description;
    this.type = type;
    this.owner = owner;
    this.createdAt = createdAt;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TrackItem trackItem = (TrackItem) obj;
      return ObjectsCompat.equals(getId(), trackItem.getId()) &&
              ObjectsCompat.equals(getUserId(), trackItem.getUserId()) &&
              ObjectsCompat.equals(getPin(), trackItem.getPin()) &&
              ObjectsCompat.equals(getDescription(), trackItem.getDescription()) &&
              ObjectsCompat.equals(getType(), trackItem.getType()) &&
              ObjectsCompat.equals(getOwner(), trackItem.getOwner()) &&
              ObjectsCompat.equals(getCreatedAt(), trackItem.getCreatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserId())
      .append(getPin())
      .append(getDescription())
      .append(getType())
      .append(getOwner())
      .append(getCreatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TrackItem {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userId=" + String.valueOf(getUserId()) + ", ")
      .append("pin=" + String.valueOf(getPin()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("type=" + String.valueOf(getType()) + ", ")
      .append("owner=" + String.valueOf(getOwner()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()))
      .append("}")
      .toString();
  }
  
  public static UserIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static TrackItem justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new TrackItem(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userId,
      pin,
      description,
      type,
      owner,
      createdAt);
  }
  public interface UserIdStep {
    PinStep userId(String userId);
  }
  

  public interface PinStep {
    BuildStep pin(String pin);
  }
  

  public interface BuildStep {
    TrackItem build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep description(String description);
    BuildStep type(String type);
    BuildStep owner(String owner);
    BuildStep createdAt(Temporal.DateTime createdAt);
  }
  

  public static class Builder implements UserIdStep, PinStep, BuildStep {
    private String id;
    private String userId;
    private String pin;
    private String description;
    private String type;
    private String owner;
    private Temporal.DateTime createdAt;
    @Override
     public TrackItem build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new TrackItem(
          id,
          userId,
          pin,
          description,
          type,
          owner,
          createdAt);
    }
    
    @Override
     public PinStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userId = userId;
        return this;
    }
    
    @Override
     public BuildStep pin(String pin) {
        Objects.requireNonNull(pin);
        this.pin = pin;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep type(String type) {
        this.type = type;
        return this;
    }
    
    @Override
     public BuildStep owner(String owner) {
        this.owner = owner;
        return this;
    }
    
    @Override
     public BuildStep createdAt(Temporal.DateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String userId, String pin, String description, String type, String owner, Temporal.DateTime createdAt) {
      super.id(id);
      super.userId(userId)
        .pin(pin)
        .description(description)
        .type(type)
        .owner(owner)
        .createdAt(createdAt);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder pin(String pin) {
      return (CopyOfBuilder) super.pin(pin);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder type(String type) {
      return (CopyOfBuilder) super.type(type);
    }
    
    @Override
     public CopyOfBuilder owner(String owner) {
      return (CopyOfBuilder) super.owner(owner);
    }
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
    }
  }
  
}
