//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace EventManager4.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Match
    {
        public int MatchId { get; set; }
        public int LeagueId { get; set; }
        public int Team1Id { get; set; }
        public int Team2Id { get; set; }
        public string Location { get; set; }
        public System.DateTime Date { get; set; }
    
        public virtual League League { get; set; }
        public virtual Team Team { get; set; }
        public virtual Team Team1 { get; set; }
    }
}