package com.amplifyframework.datastore.generated.model;


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
public final class TrackItem implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField PIN = field("pin");
  public static final QueryField USER_ID = field("userId");
  public static final QueryField DESCRIPTION = field("description");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String pin;
  private final @ModelField(targetType="String") String userId;
  private final @ModelField(targetType="String") String description;
  public String getId() {
      return id;
  }
  
  public String getPin() {
      return pin;
  }
  
  public String getUserId() {
      return userId;
  }
  
  public String getDescription() {
      return description;
  }
  
  private TrackItem(String id, String pin, String userId, String description) {
    this.id = id;
    this.pin = pin;
    this.userId = userId;
    this.description = description;
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
              ObjectsCompat.equals(getPin(), trackItem.getPin()) &&
              ObjectsCompat.equals(getUserId(), trackItem.getUserId()) &&
              ObjectsCompat.equals(getDescription(), trackItem.getDescription());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPin())
      .append(getUserId())
      .append(getDescription())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TrackItem {")
      .append("id=" + String.valueOf(getId()))
      .append("pin=" + String.valueOf(getPin()))
      .append("userId=" + String.valueOf(getUserId()))
      .append("description=" + String.valueOf(getDescription()))
      .append("}")
      .toString();
  }
  
  public static PinStep builder() {
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      pin,
      userId,
      description);
  }
  public interface PinStep {
    BuildStep pin(String pin);
  }
  

  public interface BuildStep {
    TrackItem build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep userId(String userId);
    BuildStep description(String description);
  }
  

  public static class Builder implements PinStep, BuildStep {
    private String id;
    private String pin;
    private String userId;
    private String description;
    @Override
     public TrackItem build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new TrackItem(
          id,
          pin,
          userId,
          description);
    }
    
    @Override
     public BuildStep pin(String pin) {
        Objects.requireNonNull(pin);
        this.pin = pin;
        return this;
    }
    
    @Override
     public BuildStep userId(String userId) {
        this.userId = userId;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
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
    private CopyOfBuilder(String id, String pin, String userId, String description) {
      super.id(id);
      super.pin(pin)
        .userId(userId)
        .description(description);
    }
    
    @Override
     public CopyOfBuilder pin(String pin) {
      return (CopyOfBuilder) super.pin(pin);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
  }
  
}
