
(ns lazy-loader.state
    (:require [reagent.core :as reagent]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @description
; Stored states of loaded URIs.
;
; @atom (map)
; {"/my-image.png" (map)
;   {:loaded? (boolean)
;    :load-failed? (boolean)}}
;
; @usage
; (deref URIS)
; =>
; {"/my-image.png" {:loaded? true}}
(def URIS (reagent/atom {}))
