package com.ecard.service;

import com.ecard.entity.RedpacketRule;
import com.ecard.pojo.Response;

public interface PacketService {
    Response insertPacket(RedpacketRule redpacketRule);

    Response updatePacket(RedpacketRule redpacketRule);

    Response selectPacket(Long packetId, Long drugNo, Integer page, Integer rows);

    Response selectUsablePacket(Long ptNo, Long drugNo, Integer page, Integer rows);

    Response deletePacket(Long packetId);
}
