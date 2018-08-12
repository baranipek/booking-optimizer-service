package com.booking.optimizer.utility;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * the utility component handles all list operations
 */
@Component
public class ListOperationsUtility {

    public <T> Consumer<T> splitBy(
            Predicate<T> condition,
            Consumer<T> action1,
            Consumer<T> action2) {
        return n -> {
            if (condition.test(n)) {
                action1.accept(n);
            } else {
                action2.accept(n);
            }
        };
    }

}
