package com.doomdev.fybersampel.domain.interactor;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.observers.TestSubscriber;

/**
 * Test for {@link UseCase} class
 * Created by and on 15.01.16.
 */
public class UseCaseTest {

    private UseCase useCase;
    @Mock private Scheduler newThreadScheduler ;
    @Mock private Scheduler androidThread ;

    @Before
    public void setUp() {
        this.useCase = new UseCaseTestClass(newThreadScheduler,androidThread);
    }
    @Test
    public void testUseCaseID() throws Exception{
        DefaultObserver<String> testObserver = new DefaultObserver<>();
        assertEquals(0, testObserver.getUseCaseId());
        useCase.execute(testObserver);
        assertEquals(useCase.getId(), testObserver.getUseCaseId());
    }

    @Test
    public void testSubscriptionWhenExecutingUseCase() {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        useCase.execute(testSubscriber);
        useCase.unsubscribe();

        assertThat(testSubscriber.isUnsubscribed(), is(true));
    }

    private static class UseCaseTestClass extends UseCase {

        protected UseCaseTestClass(Scheduler newThreadScheduler,Scheduler androidThread) {
            super(newThreadScheduler,androidThread);
        }

        @Override protected Observable buildUseCaseObservable() {
            return Observable.empty();
        }

        @Override public void execute(Observer observer) {
            super.execute(observer);
        }
    }

}
