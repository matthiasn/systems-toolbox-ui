(ns matthiasn.systems-toolbox-ui.spec
  (:require [cljs.spec :as s]))

(defn namespaced-keyword? [k] (and (keyword? k) (namespace k)))

(s/def :st-ui/cmp-id namespaced-keyword?)
(s/def :st-ui/view-fn fn?)
(s/def :st-ui/dom-id string?)
(s/def :st-ui/cfg map?)
(s/def :st-ui/handler-map map?)
(s/def :st-ui/lifecycle-callbacks map?)
(s/def :st-ui/state-pub-handler fn?)
(s/def :st-ui/observed-xform fn?)
(s/def :st-ui/init-fn fn?)
(s/def :st-ui/initial-state map?)

(s/def :st-ui/cmp-map
  (s/keys :req-un [:st-ui/cmp-id
                   :st-ui/view-fn
                   :st-ui/dom-id]
          :opt-un [:st-ui/lifecycle-callbacks
                   :st-ui/initial-state
                   :st-ui/init-fn
                   :st-ui/cfg
                   :st-ui/handler-map
                   :st-ui/state-pub-handler
                   :st-ui/observed-xform]))