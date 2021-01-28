package wang.michaelhai.rpncalculator.core.stack;

import lombok.Data;

/**
 *  There should be stack ID in the entity to support multiple calculator running at the same time.
 *  Since this is a demonstration project, such support is omit.
 */
@Data
public class StackModificationCursorEntity {
    private Long currentCursor;
}
