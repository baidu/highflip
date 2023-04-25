package com.baidu.highflip.editor.web.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baidu.highflip.client.HighFlipClient;
import com.baidu.highflip.editor.utils.StringUtils;

import highflip.v1.Highflip;

@Service
public class HighFlipEditorService {

    private static final Logger LOG =
            LoggerFactory.getLogger(HighFlipEditorService.class);

    public HighFlipEditorService() {

    }

    public void login(String username, String password,
                      String serverIp, int serverPort) {
        // TODO create grpc client to login
    }

    public List<Highflip.OperatorGetResponse> getOperators(String ip,
                                                           int port,
                                                           String username,
                                                           String password) {
        // TODO: construct targetUri
        String targetUriDemo ="grpc://username:password@host:port/path?query";
        // init highFlip client
        HighFlipClient highFlipClient =
                HighFlipClient.newHighFlipClient(targetUriDemo);
        // rpc call
        Iterable<String> operatorIds = highFlipClient.listOperators(0, 1000);
        List<Highflip.OperatorGetResponse> operators = new ArrayList<>();
        for(String operatorId : operatorIds) {
            Highflip.OperatorGetResponse operator =
                    highFlipClient.getOperator(operatorId);
            operators.add(operator);
        }
        return operators;
    }

    public List<Highflip.PartnerGetResponse> getPartners(String ip,
                                                         int port,
                                                         String username,
                                                         String password) {
        // TODO: construct targetUri
        String targetUriDemo = "grpc://username:password@host:port/path?query";
        // init highFlip client
        HighFlipClient highFlipClient =
                HighFlipClient.newHighFlipClient(targetUriDemo);

        Iterable<String> partnerIds = highFlipClient.listPartners(0, 1000);
        List<Highflip.PartnerGetResponse> partners = new ArrayList<>();
        for (String partnerId : partnerIds) {
            Highflip.PartnerGetResponse partner =
                    highFlipClient.getPartner(partnerId);
            partners.add(partner);
        }
        return partners;
    }

    public List<Highflip.DataGetResponse> getDataList(String ip,
                                                      int port,
                                                      String username,
                                                      String password,
                                                      String targetPartnerId) {
        // TODO: construct targetUri
        String targetUriDemo = "grpc://username:password@host:port/path?query";
        // init highFlip client
        HighFlipClient highFlipClient =
                HighFlipClient.newHighFlipClient(targetUriDemo);

        Iterable<String> dataIds = highFlipClient.listData(0, 1000);
        List<Highflip.DataGetResponse> dataList = new ArrayList<>();
        for (String dataId : dataIds) {
            Highflip.DataGetResponse data = highFlipClient.getData(dataId);
            if (StringUtils.isBlank(targetPartnerId) ||
                (StringUtils.isNotBlank(data.getPartyId()) &&
                 data.getPartyId().equals(targetPartnerId))) {
                dataList.add(data);
            }
        }
        return dataList;
    }

}
