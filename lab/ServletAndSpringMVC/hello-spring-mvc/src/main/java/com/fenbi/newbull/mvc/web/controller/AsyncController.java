package com.fenbi.newbull.mvc.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

@RestController
@Slf4j
@RequestMapping("/async")
public class AsyncController {

    @GetMapping(value = "/call")
    Callable<String> call() {
        return () -> {
            Instant start = Instant.now();
            int num = new Random().nextInt(5000);
            Thread.sleep(num);
            return "started at " + start + ", finished at " + Instant.now();
        };
    }

    private List<DeferredResult<Integer>> polls = new ArrayList<>();
    private List<ResponseBodyEmitter> streams = new ArrayList<>();

    @PostMapping(value = "/value")
    public void addValue(@RequestParam int value) throws IOException {
        polls.forEach(query -> query.setResult(value));
        polls.clear();
        for (ResponseBodyEmitter emitter : streams) {
            emitter.send("value=" + value + "\n");
        }
    }

    @DeleteMapping(value = "/value")
    public void clear(@RequestParam int value) {
        for (ResponseBodyEmitter emitter : streams) {
            emitter.complete();
        }
        streams.clear();
    }

    @GetMapping(value = "/poll")
    public DeferredResult<Integer> poll() throws IOException {
        DeferredResult<Integer> poll = new DeferredResult<>();
        polls.add(poll);
        return poll;
    }

    @GetMapping(value = "/stream")
    public ResponseBodyEmitter stream() throws IOException {
        ResponseBodyEmitter stream = new ResponseBodyEmitter();
        streams.add(stream);
        return stream;
    }

}
