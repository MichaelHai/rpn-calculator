package wang.michaelhai.rpncalculator.core.stack;

import lombok.Data;
import wang.michaelhai.rpncalculator.core.BigNumber;

import java.util.List;

@Data
public class StackModificationHistoryEntity {
    private Long id;

    private List<BigNumber> popped;
    private List<BigNumber> pushed;
}
