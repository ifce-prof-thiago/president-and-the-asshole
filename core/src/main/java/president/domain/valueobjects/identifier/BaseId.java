package president.domain.valueobjects.identifier;

import president.domain.valueobjects.ValueObject;

import java.util.Objects;
import java.util.UUID;

public abstract class BaseId implements ValueObject {

    private final UUID value;

    protected BaseId(final UUID value) {
        this.value = value;
    }

    public String getValue() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId baseId = (BaseId) o;
        return value.equals(baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
