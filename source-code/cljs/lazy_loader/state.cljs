
(ns lazy-loader.state
    (:require [reagent.core :refer [atom] :rename {atom ratom}]))

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
(def URIS (ratom {}))
