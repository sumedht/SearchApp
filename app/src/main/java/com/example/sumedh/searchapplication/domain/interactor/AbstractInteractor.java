package com.example.sumedh.searchapplication.domain.interactor;


import com.example.sumedh.searchapplication.executor.Interactor;
import com.example.sumedh.searchapplication.executor.InteractorExecutor;
import com.example.sumedh.searchapplication.executor.MainThreadExecutor;

/**
 * @author glomadrian
 */
public abstract class AbstractInteractor implements Interactor {

    private InteractorExecutor interactorExecutor;
    private MainThreadExecutor mainThreadExecutor;

    public AbstractInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        this.interactorExecutor = interactorExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public InteractorExecutor getInteractorExecutor() {
        return interactorExecutor;
    }


    public MainThreadExecutor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

}
