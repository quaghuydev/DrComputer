package vn.id.quanghuydevfs.drcomputer.controller.log;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.id.quanghuydevfs.drcomputer.dto.log.LogReqDTO;
import vn.id.quanghuydevfs.drcomputer.model.log.Log;
import vn.id.quanghuydevfs.drcomputer.service.LogService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/log")
public class LogController {
    private final LogService service;

    @GetMapping("/get-all")
    public ResponseEntity<List<Log>> getLogs() {
        return ResponseEntity.ok(service.getLogs());

    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertLog(@RequestBody LogReqDTO req) {
        return ResponseEntity.ok(service.insertLog(req));

    }

    @PostMapping("/delete/{id}")
    public void deleteLog(@PathVariable long id) {
        service.deleteLog(id);

    }
}
