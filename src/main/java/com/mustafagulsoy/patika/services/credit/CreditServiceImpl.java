package com.mustafagulsoy.patika.services.credit;

import com.mustafagulsoy.patika.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreditServiceImpl implements CreditService {

    @Override
    public boolean checkCostumer(User user) {
        float _creditRate = user.getCreditRate();
        float _limit;
        float creditLimMult = 4f;
        boolean _accepted = false;

        if (_creditRate < 500) {
            _accepted = false;
        } else if (_creditRate < 1000) {
            _accepted = true;
            if (user.getIncome() < 5000) {
                _limit = 10000 + user.getAssurance() * 0.1f;
                log.info("Credit request has been accepted for  {} {} at {}", user.getName(), user.getSurname(),new Date());
            } else if (user.getIncome() > 5000 && user.getIncome() < 10000) {
                _limit = 20000 + user.getAssurance() * 0.2f;
            } else
                _limit = 20000 + user.getIncome() * creditLimMult / 2 + user.getAssurance() * 0.25f;
        }
        else {
            _limit = 20000 + user.getIncome() * creditLimMult  + user.getAssurance() * 0.5f;
        }
        return _accepted;

    }
}
