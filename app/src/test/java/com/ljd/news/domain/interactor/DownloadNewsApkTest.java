package com.ljd.news.domain.interactor;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.NewsRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class DownloadNewsApkTest {

    private DownloadNewsApk downloadNewsApk;

    @Mock private NewsRepository mockNewsRepository;
    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp(){
        downloadNewsApk = new DownloadNewsApk(mockThreadExecutor,mockPostExecutionThread,
                mockNewsRepository);
    }

    @Test
    public void testCheckNewsUpdateUseCaseObservable(){
        downloadNewsApk.buildUseCaseObservable();
        verify(mockNewsRepository).downloadNewsApk();
        verifyNoMoreInteractions(mockNewsRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
