package com.mindhub.Homebanking;

import com.mindhub.Homebanking.utils.CardUtils;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class CardUtilsTests {
    @Test
    public void cardNumberIsCreated(){
        String cardString = CardUtils.getString();
        assertThat(cardString,is(not(emptyOrNullString())));
    }
}
