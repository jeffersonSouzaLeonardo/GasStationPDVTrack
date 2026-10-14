package com.br.manager.listener;

import com.br.manager.dto.FuelingDTO;

/** Observer notified by {@link FuelingListener} every time a fueling is read from the concentrator. */
@FunctionalInterface
public interface FuelingObserver {

    /** Called on the listener thread; UI observers must hop to the UI thread themselves. */
    void onFueling(FuelingDTO fueling);
}
