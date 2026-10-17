package com.br.manager.domain.integration.listener;

import com.br.manager.domain.integration.dto.FuelingDTO;

/** Observer notified by {@link FuelingListener} every time a fueling is read from the concentrator. */
@FunctionalInterface
public interface FuelingObserver {

    /** Called on the listener thread; UI observers must hop to the UI thread themselves. */
    void onFueling(FuelingDTO fueling);
}
