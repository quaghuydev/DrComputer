package vn.id.quanghuydevfs.drcomputer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.id.quanghuydevfs.drcomputer.dto.log.LogReqDTO;
import vn.id.quanghuydevfs.drcomputer.model.log.Log;
import vn.id.quanghuydevfs.drcomputer.repository.LogRepository;
import vn.id.quanghuydevfs.drcomputer.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository repository;
    private final UserRepository userRepository;

    public List<Log> getLogs() {
        return repository.findAll();
    }

    public void deleteLog(long id) {
         repository.deleteById(id);
    }

    public Log insertLog(LogReqDTO logdto) {
        var user = userRepository.findByEmail(logdto.getUser().getEmail()).orElseThrow();

        var log = Log.builder()
                .user(user)
                .content(logdto.getContent())
                .role(user.getRoles())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(log);
        return log;
    }
}
