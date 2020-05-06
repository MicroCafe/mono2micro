/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.aspectj.lang.ProceedingJoinPoint
 *  org.aspectj.lang.annotation.Around
 *  org.aspectj.lang.annotation.Aspect
 *  org.aspectj.lang.annotation.Pointcut
 */
package cn.springcloud.gray.concurrent.aspect;

import cn.springcloud.gray.concurrent.GrayConcurrentHelper;
import cn.springcloud.gray.concurrent.GrayExecutor;
import cn.springcloud.gray.concurrent.GrayExecutorService;
import cn.springcloud.gray.concurrent.GrayRunnable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExecutorGrayAspect {
    @Pointcut(value="execution(* java.util.concurrent.Executor.execute(..)) && args(command)")
    public void pointCut(Runnable command) {
    }

    @Around(value="pointCut(command)")
    public Object executeAround(ProceedingJoinPoint joinpoint, Runnable command) throws Throwable {
        if (joinpoint.getTarget() instanceof GrayExecutor || joinpoint.getTarget() instanceof GrayExecutorService || command instanceof GrayRunnable) {
            return joinpoint.proceed();
        }
        Runnable runnable = GrayConcurrentHelper.createDelegateRunnable(command);
        return joinpoint.proceed(new Object[]{runnable});
    }
}

