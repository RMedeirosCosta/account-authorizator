(ns account-authorizator.domain.entity.account_entity)

(defrecord Account [active-card, available-limit, violations])
