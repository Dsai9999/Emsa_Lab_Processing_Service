package com.EMSA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EMSA.entity.MessageAudit;

public interface MessageAuditRepository extends JpaRepository<MessageAudit, Long> {
}