
(ns lazy-loader.env
    (:require [lazy-loader.state :as state]))

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
  (if uri (get-in @state/URIS [uri :load-failed?])))

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
  (if uri (get-in @state/URIS [uri :loaded?])))
