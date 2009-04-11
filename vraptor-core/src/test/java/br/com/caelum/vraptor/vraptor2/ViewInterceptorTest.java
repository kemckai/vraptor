package br.com.caelum.vraptor.vraptor2;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.view.jsp.PageResult;

public class ViewInterceptorTest {

    private Mockery mockery;
    private RequestResult requestResult;
    private PageResult result;
    private ViewInterceptor interceptor;

    @Before
    public void setup() throws NoSuchMethodException {
        this.mockery = new Mockery();
        this.requestResult = new RequestResult();
        this.requestResult.setValue("ok");
        this.result = mockery.mock(PageResult.class);
        this.interceptor = new ViewInterceptor(result, requestResult);
    }

    @Test
    public void shouldForward() throws SecurityException, NoSuchMethodException, InterceptionException, IOException, ServletException {
        mockery.checking(new Expectations() {
            {
                one(result).forward("ok");
            }
        });
        interceptor.intercept(null, null, null);
        mockery.assertIsSatisfied();
    }
    class MyThrowable extends Throwable {
        private static final long serialVersionUID = 1L;
        
    }

    @Test
    public void shouldThrowCauseIfAnErrorOccurs() throws SecurityException, NoSuchMethodException, IOException, ServletException {
        final Throwable cause = new MyThrowable();
        mockery.checking(new Expectations() {
            {
                one(result).forward("ok"); will(throwException(new ServletException(cause)));
            }
        });
        try {
            interceptor.intercept(null, null, null);
        } catch (InterceptionException e) {
            assertThat(e.getCause().getCause().getClass(), is(typeCompatibleWith(MyThrowable.class)));
            mockery.assertIsSatisfied();
            return;
        }
        // cannot use annotation due to assert on cause type
        Assert.fail("Exception expected");
    }


}
