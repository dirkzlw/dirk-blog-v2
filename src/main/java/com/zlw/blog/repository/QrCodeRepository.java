package com.zlw.blog.repository;

import com.zlw.blog.po.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ranger
 * @create 2019-10-27 10:20
 */
public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
}
