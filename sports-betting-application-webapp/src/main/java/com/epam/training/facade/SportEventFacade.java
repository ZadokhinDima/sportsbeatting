package com.epam.training.facade;

import com.epam.training.dto.BetData;
import com.epam.training.dto.OutcomeData;
import com.epam.training.dto.OutcomeOddData;
import com.epam.training.dto.SportEventData;

public interface SportEventFacade {

    SportEventData createSportEvent(SportEventData sportEventData);

    BetData addBetToSportEvent(BetData betData);

    OutcomeData addOutcomeToBet(OutcomeData outcomeData);

    OutcomeOddData addOutcomeOddToOutcome(OutcomeOddData outcomeOddData);

}
