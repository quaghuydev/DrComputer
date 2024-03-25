package vn.id.quanghuydevfs.drcomputer.controller.evaluate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.id.quanghuydevfs.drcomputer.model.evaluate.Evaluate;
import vn.id.quanghuydevfs.drcomputer.service.EvaluateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/evaluate")
public class EvaluateController {
    private final EvaluateService service;

    @GetMapping("/all")
    public ResponseEntity<List<Evaluate>> getAll() {
        return ResponseEntity.ok(service.getEvaluates());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Optional<Evaluate>> getById(long id) {
        return ResponseEntity.ok(service.getEvaluateById(id));
    }
}
