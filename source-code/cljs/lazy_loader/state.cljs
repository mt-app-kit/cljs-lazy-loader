
(ns lazy-loader.state)

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
; (deref LOADERS)
; =>
; {"/my-image.png" {:loaded? true}}
(def LOADERS (atom {}))
