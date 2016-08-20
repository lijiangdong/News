package com.ljd.news.domain.interactor;

import com.ljd.news.data.repository.AvatarDataRepository;
import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetWeChatNewsTest {

    private static final String APP_KEY = "53b4892b3f764cc2a19e4896546aa8e8";
    private static final int RAWS = 10;
    private static final int PAGE = 1;

    private GetWeChatNews getWeChatNews;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private AvatarDataRepository mockAvatarDataRepository;

    @Before
    public void setUp(){
        getWeChatNews = new GetWeChatNews(mockThreadExecutor,mockPostExecutionThread,
                mockAvatarDataRepository);
    }

    @Test
    public void testWeChatUseCaseObservable(){
        getWeChatNews.buildUseCaseObservable();
        verify(mockAvatarDataRepository).getWeChatNews(APP_KEY,PAGE,RAWS,"Android");
        verifyNoMoreInteractions(mockAvatarDataRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }

}
