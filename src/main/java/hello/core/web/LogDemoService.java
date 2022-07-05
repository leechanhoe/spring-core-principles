package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger myLogger; // 스프링빈에 요청하는걸 지연해줌

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
