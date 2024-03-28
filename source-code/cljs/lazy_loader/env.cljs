
(ns lazy-loader.env
    (:require [common-state.api :as common-state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn image-load-failed?
  ; @description
  ; Returns TRUE if the given URI is already loaded but failed.
  ;
  ; @param (keyword) loader-id
  ; @param (map) loader-props
  ; {:uri (string)}
  ;
  ; @usage
  ; (image-load-failed? :my-loader {...})
  ; =>
  ; true
  ;
  ; @return (boolean)
  [_ {:keys [uri]}]
  (if uri (common-state/get-state :lazy-loader :uris uri :load-failed?)))

(defn image-loaded?
  ; @description
  ; Returns TRUE if the given URI is already loaded successfully.
  ;
  ; @param (keyword) loader-id
  ; @param (map) loader-props
  ; {:uri (string)}
  ;
  ; @usage
  ; (image-loaded? :my-loader {...})
  ; =>
  ; true
  ;
  ; @return (boolean)
  [_ {:keys [uri]}]
  (if uri (common-state/get-state :lazy-loader :uris uri :loaded?)))
