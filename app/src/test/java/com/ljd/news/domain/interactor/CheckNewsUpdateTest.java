package com.ljd.news.domain.interactor;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.NewsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
public class CheckNewsUpdateTest {

    private CheckNewsUpdate checkNewsUpdate;

    @Mock private NewsRepository mockNewsRepository;
    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        checkNewsUpdate = new CheckNewsUpdate(mockThreadExecutor,mockPostExecutionThread,
                mockNewsRepository);
    }

    @Test
    public void testCheckNewsUpdateUseCaseObservable(){
        checkNewsUpdate.buildUseCaseObservable();
        verify(mockNewsRepository).checkApkUpdate();
        verifyNoMoreInteractions(mockNewsRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
