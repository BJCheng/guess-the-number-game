package ben.learn;

import ben.learn.qualifiers.MaxNumber;
import ben.learn.qualifiers.MinNumber;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Slf4j
@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final Random random = new Random();

    @Getter
    private final int maxNumber;

    @Getter
    private final int minNumber;

    // == constructor ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber){
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        // 這裏不指定Qualifier會出錯是因為這是一個Component，container會想辦法初始化他卻不能保證誰是MaxNumber誰是MinNumber
    }

    @Override
    public int next() {
        return random.nextInt(maxNumber-minNumber) + minNumber;
    }

    @PostConstruct
    void postConstruct(){
        log.info("亂數產生器初始化完畢");
    }
}
