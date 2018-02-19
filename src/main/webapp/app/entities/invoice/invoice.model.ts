import { BaseEntity } from './../../shared';

export const enum NameOfService {
    'WORKSHOP',
    'EDUCATION',
    'SPONSORSHIP'
}

export class Invoice implements BaseEntity {
    constructor(
        public id?: number,
        public invoicenum?: string,
        public datecreated?: any,
        public nameofservice?: NameOfService,
        public unit?: number,
        public quantity?: number,
        public price?: number,
        public amount?: number,
        public customer?: BaseEntity,
    ) {
    }
}
