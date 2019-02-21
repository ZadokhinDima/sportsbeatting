package com.epam.training.sportsbeatting.exception;

        import com.epam.training.sportsbeatting.domain.PersistableObject;

public class ModelNotFoundException extends RuntimeException {

    private static final String templateMessage = "Model of class %s was not found.";
    private static final String templateMessageWithId = "Model of class %s with id %d was not found.";

    public ModelNotFoundException(final Class<? extends PersistableObject> modelClass) {
        super(String.format(templateMessage, modelClass.getSimpleName()));
    }

    public ModelNotFoundException(final Class<? extends PersistableObject> modelClass, final Long id) {
        super(String.format(templateMessageWithId, modelClass.getSimpleName(), id));
    }
}
