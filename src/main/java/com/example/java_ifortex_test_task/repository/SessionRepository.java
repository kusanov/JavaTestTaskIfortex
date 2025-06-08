package com.example.java_ifortex_test_task.repository;

import com.example.java_ifortex_test_task.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = "SELECT s.* FROM sessions s WHERE device_type = '1' AND ended_at_utc is NULL ORDER BY started_at_utc ASC LIMIT 1", nativeQuery = true)
    Session getFirstDesktopSession();



    @Query(value = "SELECT s.* FROM sessions s JOIN users u ON s.user_id = u.id WHERE u.deleted = false AND s.ended_at_utc < '2025-01-01' ORDER BY s.started_at_utc DESC", nativeQuery = true)
    List<Session> getSessionsFromActiveUsersEndedBefore2025();
}